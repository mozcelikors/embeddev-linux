FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"
SRC_URI += " file://gccbaseISystem.patch "

# Add linuxfb plugin
PACKAGECONFIG_append = " linuxfb "
