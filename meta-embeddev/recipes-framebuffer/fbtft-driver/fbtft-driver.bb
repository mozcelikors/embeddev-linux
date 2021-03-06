# Modified by Mustafa Ozcelikors <mozcelikors@gmail.com>,
#  originally developed by wallacezq from https://github.com/wallacezq/fbtft-yocto-recipe

DESCRIPTION = "fbtft  Kernel Driver Sample"
HOMEPAGE = "https://github.com/notro/fbtft.git"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
#RDEPENDS_fbtft = "kernel (${KERNEL_VERSION})"
#RDEPENDS_${PN} = "kernel"
#DEPENDS = "virtual/kernel"
PR = "r0"

SRCREV = "71994224c5ed951eab7ca9da2c919456d1632d15"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI = "git://github.com/notro/fbtft.git;protocol=http \
	   file://dma_disable.patch \
	   file://cansleep.patch \
           file://fbtftenable.scc \
           file://fbtftenable.cfg \
          "

S="${WORKDIR}/git/"

inherit module

module_do_compile() {
	bbwarn 'Kernel CC: "${KERNEL_CC}"'
	bbwarn 'Kernel LD: "${KERNEL_LD}"'
	unset CC LD
  unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CPP
  oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/fbtft" ' \
             'KDIR="${STAGING_KERNEL_DIR}"' \
             'KERNEL_VERSION="${KERNEL_VERSION}"' \
             'CC="${KERNEL_CC}"' \
             'LD="${KERNEL_LD}"'

}


module_do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/fbtft

#   for driver_ko in ${S}/$
   #install -m 0644 ${S}/fbtft*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/fbtft
   install -m 0644 ${S}/f*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/fbtft
}

SRC_URI[md5sum] = "01c531fe2a18fede560b26b9bd97726f"
SRC_URI[sha256sum] = "0beed940ae281c4e48425faa5b07fb774cd06a31c619d80e9dfff2e8d9a0507c"



#LIC_FILES_CHKSUM = "file://../../LICENSE;md5=4326bf94c79e51e9d3f8d7c106fa0fde"
