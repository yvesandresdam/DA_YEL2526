CREATE OR REPLACE FUNCTION listofemployees(myJob VARCHAR)
 RETURNS SETOF employee
 LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
myemployee employee;
BEGIN
RETURN QUERY
 SELECT * FROM employee WHERE employee.job = myJob;
END;
$BODY$;