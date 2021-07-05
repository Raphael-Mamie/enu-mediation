/*
 * Espace numerique de l'usager - enu-mediation
 *
 * Copyright (C) 2021 Republique et canton de Geneve
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.ge.ael.enu.mediation.business.validation.obsolete;

import ch.ge.ael.enu.business.domain.v1_0.NewSuggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifie qu'un message JSON de creation d'une suggestion de demarche est valide.
 */
public class NewSuggestionValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewSuggestionValidator.class);

    private static final String ID_PRESTATION = "idPrestation";

    private static final String ID_USAGER = "idUsager";

    private static final String LIBELLE_ACTION = "libelleAction";

    private static final String URL_ACTION = "urlAction";

    private static final String DATE_ECHEANCE_ACTION = "dateEcheanceAction";

    private static final String DESCRIPTION_ACTION = "descriptionAction";

    private static final String URL_PRESTATION = "urlPrestation";

    public NewSuggestion validate(NewSuggestion message) {
        LOGGER.info("Dans {}", getClass().getSimpleName());
//
//        final int MAX_SIZE_LIBELLE_ACTION = 25;
//        final int MAX_SIZE_DESCRIPTION_ACTION = 150;
//
//        checkExistence(message.getIdPrestation(), ID_PRESTATION);
//        checkExistence(message.getIdUsager(), ID_USAGER);
//        checkExistence(message.getLibelleAction(), LIBELLE_ACTION);
//        checkExistence(message.getUrlAction(), URL_ACTION);
//        checkExistence(message.getDateEcheanceAction(), DATE_ECHEANCE_ACTION);
//        checkExistence(message.getDescriptionAction(), DESCRIPTION_ACTION);
//        checkExistence(message.getUrlPrestation(), URL_PRESTATION);
//
//        checkSizeIdPrestation(message.getIdPrestation());
//        checkSizeIdUsager(message.getIdUsager());
//        checkSize(message.getLibelleAction(), 1, MAX_SIZE_LIBELLE_ACTION, LIBELLE_ACTION);
//        checkSizeUrl(message.getUrlAction(), URL_ACTION);
//        checkSizeDate(message.getDateEcheanceAction(), DATE_ECHEANCE_ACTION);
//        checkSize(message.getDescriptionAction(), 1, MAX_SIZE_DESCRIPTION_ACTION, DESCRIPTION_ACTION);
//        checkSizeUrl(message.getUrlPrestation(), URL_PRESTATION);
//
//        checkDate(message.getDateEcheanceAction(), DATE_ECHEANCE_ACTION);

        LOGGER.info("Validation OK");
        return message;  // important !
    }

}
