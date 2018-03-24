#To build SDK, use bitbake meta-toolchain

DESCRIPTION = "Embeddev-LXDE image."

LICENSE="CLOSED"

IMAGE_INSTALL = "packagegroup-core-boot \
    packagegroup-core-x11 \
    packagegroup-lxde-base \
    kernel-modules \
"

IMAGE_INSTALL_append = " nano git cmake qtbase qtchooser dbus packagegroup-core-ssh-openssh xterm"

#Framebuffer driver for tft
IMAGE_INSTALL_append = " xf86-video-fbdev"

IMAGE_INSTALL_append = " apt dpkg sudo tzdata glibc-utils localedef networkmanager pointercal xinit xkeyboard-config base-passwd liberation-fonts pkgconfig"

IMAGE_INSTALL_append = " wiringpi"

#Maybe consider connman instead of networkmanager

#vc-graphics is problematic with userland..


## SDK stuff, to build sdk use bitbake rpi-embeddev-lxde-image -c populate_sdk
# Add all static packages:  SDKIMAGE_FEATURES += "staticdev-pkgs"
SDKIMAGE_FEATURES += "staticdev-pkgs"
SDKIMAGE_FEATURES += "dev-pkgs"
TOOLCHAIN_TARGET_TASK_append = " wiringpi-dev"
##
inherit distro_features_check
REQUIRED_DISTRO_FEATURES = "x11"

IMAGE_LINGUAS ?= " "

LICENSE = "MIT"

export IMAGE_BASENAME = "rpi-embeddev-lxde-image"

inherit core-image

ENABLE_SPI_BUS = "1"
ENABLE_I2C = "1"

# qtwebengine qtwebkit ...

do_image_prepend() {

}
