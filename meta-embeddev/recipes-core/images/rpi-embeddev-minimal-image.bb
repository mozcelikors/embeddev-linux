LICENSE="CLOSED"

# Base this image on rpi-basic-image
require recipes-core/images/rpi-basic-image.bb

IMAGE_INSTALL_append = "nano git cmake qtbase qtchooser dbus connman"

ENABLE_SPI_BUS = "1"
ENABLE_I2C = "1"

# qtwebengine qtwebkit ...

do_image_prepend() {

}
