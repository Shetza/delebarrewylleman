create or replace function verifierEmpruntsMaximum_trigger() returns trigger as $BODY$
declare nbDvdEmpruntes integer;
begin
	select nombre_emprunts into nbDvdEmpruntes
			from vue_nombre_emprunts;

		if(nbDvdEmpruntes > 3) then
			RAISE EXCEPTION 'emprunts impossible(max atteint)';
	end if;
	RETURN NEW;
end;
$BODY$ LANGUAGE plpgsql ;

CREATE TRIGGER  verifierEmpruntsMax
	AFTER INSERT OR UPDATE ON  emprunts FOR EACH ROW
	EXECUTE PROCEDURE  verifierEmpruntsMaximum_trigger();