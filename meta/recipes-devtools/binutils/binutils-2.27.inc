LIC_FILES_CHKSUM="\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674\
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6\
    file://gas/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    file://include/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://include/COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://libiberty/COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7\
    file://bfd/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    "

def binutils_branch_version(d):
    pvsplit = d.getVar('PV', True).split('.')
    return pvsplit[0] + "_" + pvsplit[1]

BINUPV = "${@binutils_branch_version(d)}"

UPSTREAM_CHECK_GITTAGREGEX = "binutils-(?P<pver>\d+_(\d_?)*)"

SRCREV = "5fe53ce37f9d9dd544ec8cc9e2863b68e1a61f4c"
SRC_URI = "\
     git://sourceware.org/git/binutils-gdb.git;branch=binutils-${BINUPV}-branch;protocol=git \
     file://0002-configure-widen-the-regexp-for-SH-architectures.patch \
     file://0003-Point-scripts-location-to-libdir.patch \
     file://0004-Only-generate-an-RPATH-entry-if-LD_RUN_PATH-is-not-e.patch \
     file://0005-Explicitly-link-with-libm-on-uclibc.patch \
     file://0006-Use-libtool-2.4.patch \
     file://0007-Add-the-armv5e-architecture-to-binutils.patch \
     file://0008-don-t-let-the-distro-compiler-point-to-the-wrong-ins.patch \
     file://0009-warn-for-uses-of-system-directories-when-cross-linki.patch \
     file://0010-Fix-rpath-in-libtool-when-sysroot-is-enabled.patch \
     file://0011-Change-default-emulation-for-mips64-linux.patch \
     file://0012-Add-support-for-Netlogic-XLP.patch \
     file://0013-fix-the-incorrect-assembling-for-ppc-wait-mnemonic.patch \
     file://0014-libtool-remove-rpath.patch \
     file://0015-binutils-mips-gas-pic-relax-linkonce.diff \
     file://0015-Refine-.cfi_sections-check-to-only-consider-compact-.patch \
     file://0016-Fix-seg-fault-in-ARM-linker-when-trying-to-parse-a-b.patch \
     file://0017-Fix-the-generation-of-alignment-frags-in-code-sectio.patch \
     file://0001-ppc-apuinfo-for-spe-parsed-incorrectly.patch \
     file://CVE-2017-6965.patch \
     file://CVE-2017-6966.patch \
     file://CVE-2017-6969.patch \
     file://CVE-2017-6969_2.patch \
     file://CVE-2017-7209.patch \
     file://CVE-2017-7210.patch \
     file://CVE-2017-7614.patch \
     file://CVE-2017-9038.patch \
     file://CVE-2017-9039.patch \
     file://CVE-2017-9039_1.patch \
     file://CVE-2017-9040_and_9042.patch \
     file://CVE-2017-9041_1.patch \
     file://CVE-2017-9041_2.patch \
     file://CVE-2017-7226.patch \
     file://CVE-2017-12448.patch \
     file://CVE-2017-12449_12455_12457_1.patch \
     file://CVE-2017-12449_12455_12457.patch \
     file://CVE-2017-12451.patch \
     file://CVE-2017-12450_12452_12453_12454_12456_1.patch \
     file://CVE-2017-12450_12452_12453_12454_12456.patch \
     file://CVE-2017-7223.patch \
     file://CVE-2017-7224.patch \
     file://CVE-2017-7225.patch \
     file://CVE-2017-7227.patch \
     file://CVE-2017-7301.patch \
     file://CVE-2017-7302.patch \
     file://CVE-2017-7303.patch \
     file://CVE-2017-7304.patch \
     file://CVE-2017-8393.patch \
     file://CVE-2017-8395.patch \
     file://CVE-2017-8397.patch \
     file://CVE-2017-7300.patch \
     file://CVE-2017-8396.patch \
     file://CVE-2017-8421.patch \
     file://CVE-2017-8394_1.patch \
     file://CVE-2017-8394.patch \
     file://CVE-2017-8398.patch \
     file://CVE-2017-7299_1.patch \
     file://CVE-2017-7299_2.patch \
     file://CVE-2017-9751.patch \
     file://CVE-2017-9749.patch \
     file://CVE-2017-9746.patch \
     file://CVE-2017-9748.patch \
     file://CVE-2017-9747.patch \
     file://CVE-2017-9750.patch \
     file://CVE-2017-9752.patch \
     file://CVE-2017-9753_9754.patch \
     file://CVE-2017-9755_1.patch \
     file://CVE-2017-9755_2.patch \
     file://CVE-2017-9756.patch \
     file://CVE-2017-9745.patch \
     file://CVE-2017-9954.patch \
     file://CVE-2017-9955_1.patch \
     file://CVE-2017-9955_2.patch \
     file://CVE-2017-9955_3.patch \
     file://CVE-2017-9955_4.patch \
     file://CVE-2017-9955_5.patch \
     file://CVE-2017-9955_6.patch \
     file://CVE-2017-9955_7.patch \
     file://CVE-2017-9955_8.patch \
     file://CVE-2017-9955_9.patch \
     file://CVE-2017-14729.patch \
     file://CVE-2017-15024.patch \
     file://CVE-2017-15938.patch \
"
S  = "${WORKDIR}/git"

do_configure_prepend () {
        rm -rf ${S}/gdb ${S}/libdecnumber ${S}/readline ${S}/sim
}
