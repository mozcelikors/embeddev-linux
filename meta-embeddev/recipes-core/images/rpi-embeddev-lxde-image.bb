#To build SDK, use bitbake meta-toolchain

DESCRIPTION = "Embeddev-LXDE image."

LICENSE="CLOSED"

inherit core-image
inherit distro_features_check
inherit populate_sdk populate_sdk_qt5
export IMAGE_BASENAME = "rpi-embeddev-lxde-image"

