#To build SDK, use bitbake meta-toolchain

DESCRIPTION = "Embeddev-LXDE image."

LICENSE="CLOSED"

IMAGE_INSTALL = "packagegroup-core-boot \
    packagegroup-core-x11 \
    packagegroup-lxde-base \
    kernel-modules \
"

IMAGE_INSTALL_append = " nano git cmake qtbase qtchooser dbus connman packagegroup-core-ssh-openssh xterm"

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
