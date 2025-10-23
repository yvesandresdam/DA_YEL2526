CREATE OR REPLACE FUNCTION listofemployeeswithjob(myjob VARCHAR)
 RETURNS SETOF employee
 LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
myemployee employee;
BEGIN
RETURN QUERY
 SELECT * FROM employee WHERE employee.job = myjob;
END;
$BODY$;