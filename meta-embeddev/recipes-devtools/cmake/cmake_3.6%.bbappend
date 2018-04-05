FILESEXTRAPATHS_prepend := "${THISDIR}/cmake:"

SRC_URI_append_class-nativesdk = " \
    file://OEToolchainConfig.cmake \
    file://environment.d-cmake.sh"


do_install_append_class-nativesdk() {
    mkdir -p ${D}${SDKPATHNATIVE}/usr/share/cmake
    install -m 644 ${WORKDIR}/OEToolchainConfig.cmake ${D}${SDKPATHNATIVE}/usr/share/cmake

    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${WORKDIR}/environment.d-cmake.sh ${D}${SDKPATHNATIVE}/environment-setup.d/cmake.sh
}


FILES_${PN}_append_class-nativesdk = " ${SDKPATHNATIVE}"

FILES_${PN} += "${datadir}/cmake-${CMAKE_MAJOR_VERSION}"


BBCLASSEXTEND = "nativesdk"
