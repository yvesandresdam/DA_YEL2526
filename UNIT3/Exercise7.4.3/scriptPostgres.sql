CREATE OR REPLACE FUNCTION listofemployeeswithname(myname VARCHAR)
 RETURNS SETOF employee
 LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
myemployee employee;
BEGIN
RETURN QUERY
 SELECT * FROM employee WHERE ename LIKE CONCAT('%', myname, '%');
END;
$BODY$;