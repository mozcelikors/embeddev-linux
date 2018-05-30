FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS += "gdk-pixbuf-native"

PRINC = "8"

SRC_URI += "file://psplash-colors.h \
	          file://psplash-bar-img.png \
            file://psplash-init"

# NB: this is only for the main logo image; if you add multiple images here,
#     poky will build multiple psplash packages with 'outsuffix' in name for
#     each of these ...
SPLASH_IMAGES = "file://psplash-poky-img.png;outsuffix=default"

# The core psplash recipe is only designed to deal with modifications to the
# 'logo' image; we need to change the bar image too, since we are changing
# colors
do_configure_append () {
	cd ${S}
	cp ../psplash-colors.h ./
	# strip the -img suffix from the bar png -- we could just store the
	# file under that suffix-less name, but that would make it confusing
	# for anyone updating the assets
	cp ../psplash-bar-img.png ./psplash-bar.png
	./make-image-header.sh ./psplash-bar.png BAR
}

# Original, fine when using /dev/fb0,
#INITSCRIPT_PARAMS = "start 0 S . stop 20 0 1 6 ."
# But we have to wait for /dev/fb1
# We adjusted priority. Check where it should run by looking at /etc/rcX.d X:Runlevel,  files start with priorities, lower prio means higher priority
#INITSCRIPT_PARAMS = "start 8 S . stop 20 0 1 6 ."
INITSCRIPT_PARAMS = "start 5 S . stop 20 0 1 6 ."

# A better approach would be create one prior init script for module initialization!
