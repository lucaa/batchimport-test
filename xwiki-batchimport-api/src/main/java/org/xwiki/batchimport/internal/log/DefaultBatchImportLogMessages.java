/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.batchimport.internal.log;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

/**
 * Class to hold and provide the default static messages for the default String batch import log, in a separate class so
 * that we don't hang all this map to every instance of StringBatchImportLog.
 * 
 * @version $Id$
 */
public class DefaultBatchImportLogMessages
{
    private static Map<String, String> prettyMessages;

    static {
        prettyMessages = new HashMap<String, String>();
        prettyMessages.put("checkingzip", "Checking zip file %s");
        prettyMessages.put("cannotopenzip", "Could not open zip file %s");
        prettyMessages.put("checkingdatadir", "Checking data directory %s");
        prettyMessages.put("cannotopendatadir", "Could not open data directory %s");
        prettyMessages.put("import", "Line %s: Imported row %s in page [[%s]] without file.");
        prettyMessages.put("simimport", "Line %s: Ready to import row %s in page %s without file.");
        prettyMessages.put("ignoreduplicate", "Line %s: Ignore %s because page name was already used in this import.");
        prettyMessages.put("ignoreemptypagename",
            "Line %s: Ignore %s because page name is empty or could not be built.");
        prettyMessages.put("done", "Processing finished.");
        prettyMessages.put("toreplace", "Line %1$s: Removed document %3$s to replace with line %2$s");
        prettyMessages.put("simtoreplace", "Line %1$s: Removing document %3$s to replace with line %2$s");
        prettyMessages.put("ignorealreadyexists", "Line %s: Cannot import row %s because page %s already exists.");
        prettyMessages.put("simimportfileok", "Line %s: Ready to import row %s in page %s and imported file %s is ok.");
        prettyMessages.put("importduplicateattach",
            "Line %s: Imported row %s in page [[%s]] without file %s since it already exists.");
        prettyMessages.put("importfiledir",
            "Line %s: Imported row %s in page [[%s]] and attached all files in the %s directory.");
        prettyMessages.put("importofficefail",
            "Line %s: Imported row %s in page [[%s]] but failed importing office file %s into content.");
        prettyMessages.put("importoffice",
            "Line %s: Imported row %s in page [[%s]] and imported office file %s into content.");
        prettyMessages.put("importnooffice",
            "Line %s: Imported row %s in page [[%s]] and did not need to import the office file.");
        prettyMessages.put("importcannotreadfile",
            "Line %s: Imported row %s in page [[%s]] and failed to read the office file.");
        prettyMessages.put("errornofile",
            "Line %s: Cannot import row %s in page %s because imported file %s does not exist.");
        prettyMessages.put("simimportnofile", "Line %s: Ready to import row %s in page %s (no file attached).");
        prettyMessages.put("importnofile", "Line %s: Imported row %s in page [[%s]].");

        prettyMessages.put("importfail",
            "Line %s: Failed to import line %s to document %s because of unknown error: \"%s\"");

        prettyMessages.put("delete", "Deleted document %s from wiki %s");
        prettyMessages.put("deletefail", "Failed to delete document %s from wiki %s becauseof unknown error : \"%s\"");

        // validation errors
        prettyMessages.put("errorvalidationlength", "Line %1$s: Validation error on row %2$s: value \"%6$s\" "
            + "on column \"%5$s\" is too long for field \"%4$s\" (max length is %7$s).");
        prettyMessages.put("errorvalidationlengthdocfullname",
            "Line %s: Validation error on row %s: full name of document (\"%s\") too long (max length is %s).");
        prettyMessages.put("errorvalidationnoobject",
            "Line %1$s: Validation error on row %2$s: cannot create object of type %4$s in document %3$s.");
        prettyMessages.put("errorvalidationtypeboolean", "Line %1$s: Validation error on row %2$s: "
            + "cannot convert value \"%6$s\" on column \"%5$s\" to boolean for field \"%4$s\" "
            + "(accepted values are 'true', 'false', '1' or '0').");
        prettyMessages.put("errorvalidationtypeinteger", "Line %1$s: Validation error on row %2$s: "
            + "cannot convert value \"%6$s\" on column \"%5$s\" to integer for field \"%4$s\" "
            + "(incorrect format or value out of range).");
        prettyMessages.put("errorvalidationtypelong", "Line %1$s: Validation error on row %2$s: "
            + "cannot convert value \"%6$s\" on column \"%5$s\" to long for field \"%4$s\" "
            + "(incorrect format or value out of range).");
        prettyMessages.put("errorvalidationtypefloat", "Line %1$s: Validation error on row %2$s: "
            + "cannot convert value \"%6$s\" on column \"%5$s\" to float for field \"%4$s\" "
            + "(incorrect format or value out of range).");
        prettyMessages.put("errorvalidationtypedouble", "Line %1$s: Validation error on row %2$s: "
            + "cannot convert value \"%6$s\" on column \"%5$s\" to double for field \"%4$s\" "
            + "(incorrect format or value out of range).");
        prettyMessages.put("errorvalidationtypedate", "Line %1$s: Validation error on row %2$s: "
            + "cannot convert value \"%6$s\" on column \"%5$s\" to date for field \"%4$s\" (accepted format is %7$s).");
    }

    public static String getPrettyMessage(String messageKey, Object... parameters)
    {
        try {
            String prettyMessage = prettyMessages.get(messageKey);
            if (prettyMessage == null) {
                return defaultFormat(messageKey, parameters);
            }
            return String.format(prettyMessages.get(messageKey), parameters);
        } catch (IllegalFormatException e) {
            return defaultFormat(messageKey, parameters);
        }
    }

    protected static String defaultFormat(String messageKey, Object... parameters)
    {
        StringBuffer result = new StringBuffer("messageKey");

        for (Object param : parameters) {
            result.append(" ");
            result.append(param);
        }

        return result.toString();
    }
}
