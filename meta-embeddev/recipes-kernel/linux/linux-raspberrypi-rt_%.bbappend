# Author: Mustafa Ozcelikors <mozcelikors@gmail.com>

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

SRC_URI += "file://disable_spidev.cfg"
SRC_URI += "file://plymouth_initrdenable.cfg"
SRC_URI += "file://git/arch/arm/boot/dts/rpi-display.dts"

# Add following to your distro.conf (embeddev.conf)
# Compile and add custom devicetree for TFT display for early module initialization
KERNEL_DEVICETREE_append = " overlays/rpi-display.dtbo "
# Add .dts file to linux-traspberrypi-rt/git/arch/arm/boot/dts/rpi-display.dts in this directory.
