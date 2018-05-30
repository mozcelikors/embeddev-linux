
do_install_append() {
	# Remove test binaries
	rm -rf ${D}${bindir}

	# Rename the library to have version
	mv ${D}${libdir}/libraspicam_cv.so ${D}${libdir}/libraspicam_cv.so.${PV}
	ln -sf libraspicam_cv.so.${PV} ${D}${libdir}/libraspicam_cv.so 
}
