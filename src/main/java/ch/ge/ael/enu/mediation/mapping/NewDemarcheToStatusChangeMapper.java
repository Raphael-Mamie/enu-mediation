package ch.ge.ael.enu.mediation.mapping;

import ch.ge.ael.enu.mediation.business.domain.DemarcheStatus;
import ch.ge.ael.enu.mediation.business.domain.NewDemarche;
import ch.ge.ael.enu.mediation.business.domain.StatusChange;

import java.time.format.DateTimeFormatter;

import static ch.ge.ael.enu.mediation.business.domain.DemarcheStatus.DEPOSEE;
import static ch.ge.ael.enu.mediation.business.domain.DemarcheStatus.EN_TRAITEMENT;

/**
 * Transforme une requete de creation de demarche (NewDemarche) en une requete de changement d'etat (StatusChange).
 * L'etat (par ex. SOUMIS) que doit avoir la requete de changement d'etat est donne en parametre du constructeur.
 */
public class NewDemarcheToStatusChangeMapper {

    private final DemarcheStatus demarcheStatus;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public NewDemarcheToStatusChangeMapper(DemarcheStatus demarcheStatus) {
        this.demarcheStatus = demarcheStatus;
    }

    public StatusChange map(NewDemarche newDemarche) {
        StatusChange statusChange = new StatusChange();
        statusChange.setIdPrestation(newDemarche.getIdPrestation());
        statusChange.setIdUsager(newDemarche.getIdUsager());
        statusChange.setIdDemarcheSiMetier(newDemarche.getIdDemarcheSiMetier());
        statusChange.setNouvelEtat(demarcheStatus.name());
        if (demarcheStatus == DEPOSEE) {
            statusChange.setDateNouvelEtat(newDemarche.getDateDepot().format(FORMATTER));
        } else if (demarcheStatus == EN_TRAITEMENT) {
            statusChange.setDateNouvelEtat(newDemarche.getDateMiseEnTraitement().format(FORMATTER));
            statusChange.setLibelleAction(newDemarche.getLibelleAction());
            statusChange.setUrlAction(newDemarche.getUrlAction());
            statusChange.setTypeAction(newDemarche.getTypeAction());
            statusChange.setDateEcheanceAction(newDemarche.getDateEcheanceAction());
        }

        return statusChange;
    }

}
