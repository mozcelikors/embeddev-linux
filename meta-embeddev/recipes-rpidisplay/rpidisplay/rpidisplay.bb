# Gonna have to figure out how to compile an app with yocto that uses its SDK.
# Keywords: eSDK,  check rover-app.bb 

DESCRIPTION = "rpidisplay Qt application"
SECTION = "examples"
LICENSE = "CLOSED"
PR = "r0"

DEPENDS = "cmake"
RDEPENDS_${PN} = "bash qtbase"

SRC_URI = "git://github.com/mozcelikors/rpiDisplay_CMake.git;protocol=https"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
EXTRA_OECMAKE += "-Dpkg_config_libdir=${libdir} -DCMAKE_BUILD_TYPE=Debug"

inherit pkgconfig cmake

do_install() {
    install -d ${D}/home/root/projects
    install -D build/* ${D}/home/root/projects
}
