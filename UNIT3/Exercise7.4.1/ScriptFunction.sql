CREATE OR REPLACE FUNCTION listofemployeeswithsalesman()
 RETURNS SETOF employee
 LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
myemployee employee;
BEGIN
RETURN QUERY
 SELECT * FROM employee WHERE employee.job = 'SALESMAN';
END;
$BODY$;;