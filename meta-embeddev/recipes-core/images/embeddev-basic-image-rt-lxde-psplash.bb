##############################
#  Embeddev Basic Realtime Image with Psplash, Tuned for Raspberry Pi Zero-W
#
#  Author: Mustafa Ozcelikors <mozcelikors@gmail.com>
#
#  Dependencies:
#    meta:rocko
#    meta-yocto-bsp:rocko
#    meta-poky:rocko
#    meta-openembedded/meta-oe:rocko
#    meta-openembedded/meta-multimedia:rocko
#    meta-openembedded/meta-networking:rocko
#    meta-openembedded/meta-python:rocko
#    meta-openembedded/meta-initramfs:rocko
#    meta-lxde:rocko
#    meta-openembedded/meta-gnome:rocko
#    meta-raspberrypi:rocko
#    meta-raspberrypi-rt-sv:master
#    meta-qt5:rocko
#    meta-qt5-extra:rocko
#    meta-embeddev:master
#
##############################

################ Automatize the Following!!!!!###############################################
# To initialize TFT screen very early, compile a device tree using device tree compiler (from Kernel)
#   cd ~/pokyRT/poky/build/tmp/work/raspberrypi0_wifi-embeddev-linux-gnueabi/linux-raspberrypi-rt/1_4.9.77+gitAUTOINC+783daf505c-r0/linux-raspberrypi0_wifi-standard-build/scripts/dtc
#   ./dtc -@ -I dts -O dtb -o ./rpi-display.dtbo ~/rpi-display-overlay.dts
# Find the device tree at meta-embeddev/extra-stuff
# TODO:  Adjust touch driver in this driver.... also in the PCB.., also have ads7846-overlay.dts and ads7846.dtbo
# After device tree compilation,
# Copy to SD card image
#   cp rpi-display-overlay.dtbo /media/mozcelikors/raspberrypi/overlays/
# Add to config.txt
#   dtoverlay=rpi-display
# Add to cmdline.txt
#   logo.nologo
# Following is optional:
#   fbcon=map:10 logo.nologo rootflags=commit=120,data=writeback elevator=deadline consoleblank=0 noatime nodiratime data=writeback
# Then deploy the app,
#  sudo mkdir -p /media/mozcelikors/[TAB]/home/root/projects
#  sudo cp -r ~/qtCreatorWorkspace/rpiDisplay_CMake/build/* /media/mozcelikors/[TAB]/home/root/projects


DESCRIPTION = "Embeddev Basic Realtime Image with Psplash, Tuned for Raspberry Pi Zero-W"

# Core #######################################################################################
# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

DISTRO_FEATURES_append = "x11 systemd wiringPi"

PACKAGES_CORE_append = " \
                       packagegroup-core-boot \
                       packagegroup-core-x11 \
                       "

PACKAGES_DESKTOP_append = " \
                           packagegroup-lxde-extended \
                           lxdm \
                           xserver-common \
                           xserver-xorg-extension-dbe \
                           xserver-xorg-extension-extmod \
                           xauth \
                           xhost \
                           xset \
                           xinit \
                           xterm \
                           setxkbmap \
                           \
                           xrdb \
                           xorg-minimal-fonts xserver-xorg-utils \
                           liberation-fonts \
                           gdk-pixbuf-loader-png \
                           gdk-pixbuf-loader-jpeg \
                           gdk-pixbuf-loader-gif \
                           gdk-pixbuf-loader-xpm \
                           shared-mime-info \
                           adwaita-icon-theme-symbolic \
                           udev \
                           "

# Kernel and Boot ###########################################################################

# Add following to your distro.conf (embeddev.conf)
# Compile and add custom devicetree for TFT display for early module initialization
#KERNEL_DEVICETREE_append = " overlays/rpi-display.dtbo "

# Include modules in rootfs
PACKAGES_KERNEL_append = " \
                          kernel-modules \
                          "


#PACKAGES_KERNEL_append = " \
#                          kernel-modules \
#                          fbtft-driver \
#                          "

#KERNEL_MODULE_AUTOLOAD += " fbtft "
#KERNEL_MODULE_AUTOLOAD += " fb_ili9341 "

# To speed up boot:
#Replace rootwait in /boot/cmdline.txt to: rootflags=commit=120,data=writeback elevator=deadline consoleblank=0 noatime nodiratime data=writeback rootwait quiet
# or simply uncomment the following
# CMDLINE_append = " rootflags=commit=120,data=writeback elevator=deadline consoleblank=0 noatime nodiratime data=writeback quiet "

# rpi-basic-image-rt #########################################################################
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-raspberrypi-rt-dev":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-raspberrypi-rt to enable it")
}
PACKAGES_RPI_append = " \
                  linux-firmware-bcm43430 \
                  rt-tests \
                  hwlatdetect \
                  ntp \
                  wpa-supplicant \
                  iw \
                  wireless-tools \
                 "

# Package Management ###########################################################################

IMAGE_FEATURES_append = " package-management "
#Change PACKAGE_CLASSES to package_deb in local.conf
#IMAGE_INSTAPP_append = " apt "

# Raspbery Pi Features #########################################################################

ENABLE_SPI_BUS = "1"
ENABLE_I2C = "1"
ENABLE_UART = "1"

# Disable boot logo - need to be put on layer.conf
#CMDLINE_append = " logo.nologo "

# Basics #######################################################################################

PACKAGES_UTILITY_append = " nano git cmake packagegroup-qt5-toolchain-target dbus "
#Framebuffer driver for tft
PACKAGES_UTILITY_append = " xf86-video-fbdev"
PACKAGES_UTILITY_append = " chkconfig glibc glibc-utils localedef base-passwd pkgconfig"
PACKAGES_RPI_append = " wiringpi"

#Important for deployment - both host and target should have this!
PACKAGES_UTILITY_append = " rsync "

# Network ###############################################################################
# or networkmanager
PACKAGES_NETWORK_append = " connman "

# Splash Screen ################################################################################
# Common
IMAGE_FEATURES += "splash"

# Use psplash splash screen
SPLASH = "psplash-raspberrypi"

#Switch from psplash to Plymouth Splash screen
#SPLASH = "plymouth"
#IMAGE_INSTALL_remove = " psplash "
#IMAGE_INSTALL_append = " plymouth plymouth-set-default-theme initramfs-tools busybox udev sed dpkg findutils "
# THE FOLLOWING in cmdline.txt IS A MUST! BE SURE IT IS ADDED
#CMDLINE_append = " quiet splash nomodeset plymouth.ignore-serial-consoles consoleblank=0"


# Following is needed in the layer.conf. Uncomment them in layer.conf to disable initramfs involving image.
#INITRAMFS_IMAGE="embeddev-initramfs-image"
#INITRAMFS_IMAGE_BUNDLE = "1"
#KERNEL_INITRAMFS = "-initramfs"

#### Following is just an info we don't use anymore ######
# Now, use this image and, SSH into the board,
# cd /boot;mkdir temp;export TMPDIR=/boot/temp;mkinitramfs -o initrd.img-$(uname -r)
# sh -x update-initramfs -u -k all
# plymouth-set-default-theme --list
# plymouth-set-default-theme resin
# update-initramfs -u -v -t
# cp /boot/initrd.img-$(uname -r) /boot/uboot/
# In  /boot/uboot/uEnv.txt:
#   initrd_file=<image name in /boot/>
#   optargs=splash plymouth.ignore-serial-consoles ${optargs}
# Restart
# Add to config.txt in boot partition:
#  initramfs <image name in /boot> 0xc0a23000
#  ramfsfile="<image name in /boot>"
#  ramfsaddr=0xc0a23000
# Build initramfs image with bitbake -R conf/initramfs.conf core-image-base

### To test plymouth
#killall plymouthd
#FRAMEBUFFER=/dev/fb1 plymouthd --mode=boot --kernel-command-line="fbcon=map:10 quiet splash plymouth.ignore-serial-consoles"
#plymouth show-splash
#plymouth message --text="hello world"
# Screen unblank: echo 0 > /sys/class/graphics/fb0/blank

# Init Scripts ###############################################################################

PACKAGES_CUSTOM_append = " initscriptrpidisplay "

# Image Generation ###########################################################################

# Activate initramfs. cpio.gz will be generated, which can be used to generate initramfs
#IMAGE_FSTYPES_append = " cpio.gz"

# Cross-compilation and SDK ##################################################################

# Build qt sdk using bitbake meta-toolchain-qt5
# or simply bitbake meta-toolchain
# TODO: CREATE A NEW IMAGE RECIPE  for meta-toolchain-qt5  + TOOLCHAIN_TASKs below!!

# May be Needed for cross-compilation / host tasks - not needed if specified with TARGET_..._TASK variables.
#SDKIMAGE_FEATURES += "staticdev-pkgs"
#SDKIMAGE_FEATURES += "dev-pkgs"
#IMAGE_FEATURES += "dev-pkgs"

#Copy missing OEToolchainConfig.cmake to the directory -manually for now-

#TOOLCHAIN_HOST_TASK_append = " nativesdk-packagegroup-qt5-toolchain-host"
#CMake conflicts with the qt packagegroup, cmake is a must, when qt packagegroup is$
TOOLCHAIN_HOST_TASK_append = " nativesdk-cmake nativesdk-glibc nativesdk-cmake-dev "
TOOLCHAIN_TARGET_TASK_append = " cmake wiringpi-dev glibc glibc-utils"

# Debugging ##################################################################################

#EXTRA_IMAGE_FEATURES_append = " tools-debug tools-profile "

# Image Install ##############################################################################

IMAGE_INSTALL_append = " \
                       ${PACKAGES_CORE} \
                       ${PACKAGES_KERNEL} \
                       ${PACKAGES_DESKTOP} \
                       ${PACKAGES_UTILITY} \
                       ${PACKAGES_RPI} \
                       ${PACKAGES_CUSTOM} \
                       ${PACKAGES_NETWORK} \
                       "

# Remove Features ############################################################################

# Dropbear is faster than openssh!!!!!
IMAGE_FEATURES_append = " ssh-server-dropbear  "

# Remove Wayland since we don't need it and Qt complains!
DISTRO_FEATURES_remove = " qtwayland wayland"


do_image_prepend(){

}
