# First line enables the camera..

do_deploy_append() {
	sed -i -e 's/#start_x=.*/start_x=1/' ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
        echo "dtparam=i2c_arm=on" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "dtparam=i2c1=on" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
        echo "dtparam=spi=on" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
        echo "gpu_mem=256" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt

}

