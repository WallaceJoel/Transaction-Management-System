# Transaction-Management-System
Utilizes Jave to develop a Transaction Management System to process banking transactions.
The banking transactions will be processed through the standard I/O (console input and output.) The transactions may
include open a new account, close an existing account, deposit funds to an existing account, withdraw funds from an
existing account, print the list of accounts and print the account statements. The system maintains an account database,
which may include 3 different types of bank accounts listed in the table below. Same person can open different types
of accounts. The interest rates and fee schedules are different based on the account types and account options.

The TransactionManager class is the user interface class that handles the transactions and displays the
results on the console. You can define the data fields and private methods you need.
Each transaction begins with a two-letter command identifying the transaction type and account type,
followed by the data tokens and ends with a new line character (\n). The two-letter commands are case-
sensitive, which means the commands with lowercase letters are invalid!

**Sample Input**
o

p

c

OA John Doe 500 1/1/2010 true

cc John Doe 500 1/1/2010 true

cs John Doe 500 1/1/2010 true

cm John Doe 500 1/1/2010 true

oc John Doe 500 1/1/2010 true

os John Doe 500 1/1/2010 true

om John Doe 500 1/1/2010 true

CA John Doe 500 1/1/2010 true

cc John Doe 500 1/1/2010 true

cs John Doe 500 1/1/2010 true

cm John Doe 500 1/1/2010 true

pn

pd

pa

PA

PN

PD

CC John Doe

CS John Doe

CM John Doe

OC John Doe 500 1/1/2010 true

OS John Doe 500 1/1/2010 true

OM John Doe 500 1/1/2010

OC John Doe 500 1/1/2010 true

OS John Doe 500 1/1/2010 true

OM John Doe 500 1/1/2010

PA

WC John Doe 100

WS John Doe 100

WM John Doe 100

PA

DC John Doe 100

DS John Doe 100

DM John Doe 100

PA

WC JOHN DOE 100

DS JANE DOE 100

OC Jane Doe 500 13/1/2010 false

OC Jane Doe 500 12/32/2010 false

OC Jane Doe 500 2/29/2010 false

OC Jane Doe 500 2/29/2008 false

OS Jane Doe 500 11/31/2019 false

OS Jane Doe 500 11/1/2019 false

PA

CC John Doe

CM John Doe

PA

OC Richard Scanlan 1500 7/31/2014 true

OC Jason Brown 300.123 6/28/2015 false

OS Mary Johnson 2000.87654 7/31/2014 true

OS Charlie Brown 800 6/28/2015 false

OM Mary Johnson 456.78 7/10/2014

OM Charlie Brown 600.33 6/2/2015

OM Forever Young 3634.45 2/21/2020

OC Jerry Anderson 1001.4 9/23/2020 false

OS Tom Moore xxx 9/23/2020 false

OS Tom Moore 1001.4 9/23/2020 FALS

E

OC Tom Moore 1001.4 9/23/2020 flash

PA

WC Jerry Anderson xxx

DC Jerry Anderson xxx

WM Forever Young 100

WM Forever Young 100

WM Forever Young 100

WM Forever Young 100

WM Forever Young 100

WM Forever Young 100

WM Forever Young 100

WM Forever Young 10000

PA

PN

WS Charlie Brown 600

PD

Q
