package test

import test.JavaB
import test.XtendC

@SuppressWarnings("just here to trigger annotation processing")
class XtendA extends JavaB {
	
	def JavaB test2(XtendC s) {
		return s.foo.newJavaB
	}
	
	def JavaB newJavaB() {
		return new JavaB()
	}
	
}