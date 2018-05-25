FILESEXTRAPATHS_prepend := "${THISDIR}/init-ifupdown-1.0:"

#Replace the /etc/network/interfaces with a lighter version initially!

SRC_URI += "file://interfaces"

