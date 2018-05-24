# Simple initramfs image. Mostly used for live images.
DESCRIPTION = "Raspberry Pi Zero W-based Initramfs image for Embeddev-OS "

include recipes-core/images/rpi-basic-image.bb


# To be able to use this initramfs image, have following kernel config parameters:
#   (i.e append to linux/linux-raspberrypi as meta-embeddev does)
#   CONFIG_BLK_DEV_INITRD=y
#   CONFIG_INITRAMFS_SOURCE=""
#   CONFIG_RD_GZIP=y

# After recipe is obtained, use:
# (sudo apt-get install u-boot-tools) if you haven't already.
# mkimage -A arm -O linux -T ramdisk -n "Initial Ram Disk" -d embeddev-initramfs-image-raspberrypi0-wifi-20180522111733.rootfs.cpio.gz initramfs.img
# Deploy initramfs.img to boot partition of the Raspberry Pi SD Image
# Add to config.txt:
#  ramfsaddr=0x00000000
#  ramfsfile=initramfs.img
#  initramfs initramfs.img 0x00000000
# Add to cmdline.txt
#  initrd=initramfs.img
# Check dmesg from inside target and adjust the memory
#  Example error:
#  INITRD: 0x00a00000+0x00781dc5 overlaps in-use memory region - disabling initrd

# initramfs-framework-base
# initramfs-live-boot
# udev
# busybox
# ${ROOTFS_BOOTSTRAP_INSTALL}

# Not really: this will install all kernel modules: kernel-modules

BASEPACKS = " \
    ${ROOTFS_BOOTSTRAP_INSTALL} \
    initramfs-live-boot \
    udev \
    busybox \
    base-passwd \
"

IMAGE_INSTALL = " ${BASEPACKS} \
                  plymouth plymouth-set-default-theme  \
                "

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = "splash"
SPLASH = "plymouth"


export IMAGE_BASENAME = "embeddev-initramfs-image"
IMAGE_LINGUAS = ""

#inherit core-image
LICENSE = "MIT"


#do_image_prepend(){
#
#}


IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

# Adjusted for RPi Zero-W
#IMAGE_ROOTFS_MAXSIZE = "12288"
#IMAGE_ROOTFS_EXTRA_SPACE = "0"
#IMAGE_ROOTFS_ALIGNMENT = "1"
#IMAGE_ROOTFS_SIZE = "8192"
#IMAGE_ROOTFS_EXTRA_SPACE = "0"
#IMAGE_ROOTFS_MAXSIZE = "12288"


# Following is needed in the layer.conf
#INITRAMFS_IMAGE="embeddev-initramfs-image"
#INITRAMFS_IMAGE_BUNDLE = "1"
#KERNEL_INITRAMFS = "-initramfs"

# THE FOLLOWING in cmdline.txt IS A MUST! BE SURE IT IS ADDED
CMDLINE_append = " quiet splash nomodeset plymouth.ignore-serial-consoles consoleblank=0"


BAD_RECOMMENDATIONS += "busybox-syslog"

# Use the same restriction as initramfs-live-install
#COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
COMPATIBLE_HOST = "(arm|aarch64|i.86|x86_64).*-linux"
#COMPATIBLE_MACHINE = "raspberrypi0-wifi"
