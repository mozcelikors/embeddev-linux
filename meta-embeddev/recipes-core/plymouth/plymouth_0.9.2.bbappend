FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG_append = " drm gtk initrd "

PLYMOUTH_THEME ?= "solar"
PLYMOUTH_SHOWDELAY ?= "5"

DEPENDS_append = " gtk+3"

set_plymouthd_var () {
    if ! grep '^$1=' src/plymouthd.conf; then
        echo "$1=$2" >>src/plymouthd.conf
    else
        sed -i -e "/^$1=/s/=.*/=$2/" src/plymouthd.conf
    fi
}

do_compile () {
    cp -f "${S}/src/plymouthd.conf" src/
    sed -i -e '/^#.Daemon/s/#//' src/plymouthd.conf
    set_plymouthd_var Theme "${PLYMOUTH_THEME}"
    set_plymouthd_var ShowDelay "${PLYMOUTH_SHOWDELAY}"
}
