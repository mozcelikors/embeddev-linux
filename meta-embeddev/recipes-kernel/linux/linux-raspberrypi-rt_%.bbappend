# Author: Mustafa Ozcelikors <mozcelikors@gmail.com>
# Disable spidev to FBTFT to use.

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-rt:"

SRC_URI += "file://disable_spidev.cfg"
