require ruby.inc

SRC_URI += " \
           file://ruby-CVE-2017-9224.patch \
           file://ruby-CVE-2017-9226.patch \
           file://ruby-CVE-2017-9227.patch \
           file://ruby-CVE-2017-9228.patch \
           file://ruby-CVE-2017-9229.patch \
           "

SRC_URI[md5sum] = "a00e0d49b454f4c0e528e7852d642925"
SRC_URI[sha256sum] = "fd0375582c92045aa7d31854e724471fb469e11a4b08ff334d39052ccaaa3a98"

# it's unknown to configure script, but then passed to extconf.rb
# maybe it's not really needed as we're hardcoding the result with
# 0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch
UNKNOWN_CONFIGURE_WHITELIST += "--enable-wide-getaddrinfo"

PACKAGECONFIG ??= ""
PACKAGECONFIG += "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)}"

PACKAGECONFIG[valgrind] = "--with-valgrind=yes, --with-valgrind=no, valgrind"
PACKAGECONFIG[gpm] = "--with-gmp=yes, --with-gmp=no, gmp"
PACKAGECONFIG[ipv6] = ",--enable-wide-getaddrinfo,"

EXTRA_AUTORECONF += "--exclude=aclocal"

EXTRA_OECONF = "\
    --disable-versioned-paths \
    --disable-rpath \
    --disable-dtrace \
    --enable-shared \
    --enable-load-relative \
"

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

PACKAGES =+ "${PN}-ri-docs ${PN}-rdoc"

SUMMARY_${PN}-ri-docs = "ri (Ruby Interactive) documentation for the Ruby standard library"
RDEPENDS_${PN}-ri-docs = "${PN}"
FILES_${PN}-ri-docs += "${datadir}/ri"

SUMMARY_${PN}-rdoc = "RDoc documentation generator from Ruby source"
RDEPENDS_${PN}-rdoc = "${PN}"
FILES_${PN}-rdoc += "${libdir}/ruby/*/rdoc ${bindir}/rdoc"

FILES_${PN} += "${datadir}/rubygems"

BBCLASSEXTEND = "native"
