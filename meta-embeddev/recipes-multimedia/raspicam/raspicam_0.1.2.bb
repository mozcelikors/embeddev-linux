#
# Bitbake recipe for raspicam_0.1.2 module
# Recipe created by: M.Ozcelikors <mozcelikors@gmail.com>
#

DESCRIPTION = "raspicam" 
SECTION = "multimedia"
#LICENSE = "CLOSED" is useful for testing purposes. 
LICENSE = "BSD" 

# This directory contains most common licenses in case no file named COPYING or LICENSE is provided: ${COMMON_LICENSE_DIR}
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb"

PR = "r0" 

# If your build system does not have opencv recipe, then you can test still by commenting below line
# The following is required for this recipe to be cross-compiled.
DEPENDS = "opencv userland"
PROVIDES = "raspicam"

# For do_package
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

FILES_${PN} += " \
	${libdir}/lib*so.* \
	${libdir}/cmake/*"

FILES_${PN}-dev += " ${libdir}/lib*so"

# Runtime dependencies of created packages
RDEPENDS_${PN}-dev = "glog-dev"
RDEPENDS_${PN}-dbg = ""

#Fetching from git repo, ${PV} is package version automatically extracted from filename
#SRC_URI = "git://github.com/6by9/raspicam-0.1.3.git;protocol=https;branch=master"
SRC_URI = "git://github.com/cedricve/raspicam.git;protocol=https;branch=master"

# SHA1-Hash when downloading from git
#SRCREV = "${AUTOREV}"
SRCREV = "35ace996cbcb7d2951db0fe50f3f97c3e1758a4c"

# This should be correct for git-based fetching
S = "${WORKDIR}/git" 

inherit pkgconfig cmake

