jar:
	jar -cvf jsonschema-1.0.1.jar LICENSE NOTICE
	cd bin && jar -uvf ../jsonschema-1.0.1.jar net
