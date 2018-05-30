# Author: Mustafa Ozcelikors <mozcelikors@gmail.com>

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

SRC_URI += "file://disable_spidev.cfg"
SRC_URI += "file://plymouth_initrdenable.cfg"
SRC_URI += "file://git/arch/arm/boot/dts/rpi-display.dts"
