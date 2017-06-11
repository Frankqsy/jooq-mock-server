# jooq-mock-server

you can use jooq to mock a database to do unit test without connecting to a real database.

this is especially useful when you test jdbc or jdbc pool with ut.

the jooq-mock-server shows you how to use jooq to implement the function above.

the simple server returns the fixed result, it can only show that the connection is ok.

while the simulate server acts as a real database. you can use it to query just the data that you insert(update), and you can also delete the data, because it implements the sql parse.

Copyright@org.daisy.stevin
