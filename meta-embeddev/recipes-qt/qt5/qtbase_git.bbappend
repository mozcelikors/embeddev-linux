FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"
SRC_URI += " file://gccbaseISystem.patch "

#Include linuxfb plugin
PACKAGECONFIG_append = " linuxfb "

