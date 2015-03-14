# A testing tool for running tests against a service bus #

We’ve put together a tool to allow you to fire off tests to ALSB and check the results via a set of validators.
It is based on JUnit and HttpUnit, and it allows you to configure both tests and validators via Jython.
The tests are defined in testdefinitions.py and the validators in validatordefinitions.py. Both of these files are used by the script RunTests.py, which in turn is invoked by the RunPythonTests class.

The tests contain a test name, a test request XML file, a test description, and a set of validators to use.
Here is a sample test config of a test with 4 validators:

["3481","testXML/general/[Bug3481](https://code.google.com/p/guavatools/issues/detail?id=81).xml","[boron](boron.md) premium variations. id attribute in correct place", ['is origo message','is origo response message','has warning status','has correct premium variation id']],

A validator definition contains: A name, a test type and some kind of validator XPath.

'cover\_period has one child': XMLObjectTest("Cover period has one child",XMLObjectTest.EQUALS,"count(//product/risk\_benefit/cover\_period/**)","1"),**


There are a few dozen example tests included in this project to give you some idea of how it would work.

The test rig endpoint is configured using the properties file ALSBTest.properties. Here you can configure whether the tool tests your local ALSB, your mate’s ALSB, or some other environment
The only things we can’t test at the moment with this tool are environments that use HTTPS, becuase I haven’t figured out how to get HttpUnit working with SSL yet (can’t be that hard though can it??!)

Check it out, and run this class: com.zurich.ep.protection.alsb.test.RunPythonTests