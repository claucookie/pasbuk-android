package es.claucookie.pasbuk.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.model.dao.PassBooksDAO;
import es.claucookie.pasbuk.model.dto.PassBookCompaniesDTO;
import es.claucookie.pasbuk.model.dto.PassBookCompanyDTO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.model.dto.PassBooksDTO;

/**
 * Created by claucookie on 15/03/15.
 */
public class PreferencesHelper {

    public static final String FAVORITES_PREFERENCES = "FAVORITES_PREFERENCES";
    public static final String PASSES = "passes";
    public static final String SORT_BY = "sort_by";
    public static final String DATE_FORMAT = "date_format";
    public static final String HIDE_PASSED_EVENTS = "hide_passed_events";
    private static final String TAG = PreferencesHelper.class.getName();
    private static final String SALT = "´gwek,v'b op'&%DFTRHFGFTv,tyjhjk`Ç'`¡u8495ubsrw";
    private SharedPreferences memoryPreferences;

    private PreferencesHelper() {
    }

    public static PreferencesHelper getInstance() {
        return FavoritesLogicHolder.instance;
    }

    private SharedPreferences preferences(Context context) {
        if (memoryPreferences == null) {
            memoryPreferences = context.getSharedPreferences(FAVORITES_PREFERENCES, Context.MODE_PRIVATE);
        }
        return memoryPreferences;
    }

    private void savePasses(Context context, PassBooksDTO passes) {
        if (context != null) {
            try {
                preferences(context).edit().putString(PASSES, PassBooksDAO.getInstance().serialize(passes).toString()).apply();
            } catch (JSONException je) {
                LogHelper.logE(TAG, je.getMessage());
            }
        }
    }

    public void savePassBook(Context context, PassBookDTO pass) {
        if (context != null) {
            PassBooksDTO savedPasses = getSavedPasses(context);
            if (savedPasses != null && pass != null) {
                if (!pass.existsInside((ArrayList<PassBookDTO>) savedPasses.getPasses())) {
                    // Add current time
                    pass.setUpdateDate((new Date()).getTime());
                    savedPasses.getPasses().add(pass);
                    LogHelper.logD(TAG, "Passbook saved!!!!");

                    savePasses(context, savedPasses);

                } else {
                    // Update pass
                    updatePassBook(context, pass, savedPasses);
                }
            }


        }
    }

    private void updatePassBook(Context context, PassBookDTO pass, PassBooksDTO savedPasses) {
        if (context != null) {
            if (savedPasses != null && pass != null && savedPasses.getPasses() != null) {
                for (int i = 0; i < savedPasses.getPasses().size(); i++) {
                    if (savedPasses.getPasses().get(i) != null &&
                            savedPasses.getPasses().get(i).getSerialNumber() != null &&
                            savedPasses.getPasses().get(i).getSerialNumber().equals(pass.getSerialNumber())) {

                        savedPasses.getPasses().set(i, pass);
                        savedPasses.getPasses().get(i).setUpdateDate((new Date()).getTime());
                        break;
                    }
                }
            }
            if (savedPasses == null) savedPasses = new PassBooksDTO();

            savePasses(context, savedPasses);
        }
    }

    public PassBooksDTO getSavedPasses(Context context) {
        PassBooksDTO savedPasses = new PassBooksDTO();
        try {
            if (context != null) {
                String savedString = preferences(context).getString(PASSES, "{}");
                if (savedString != null) {
                    savedPasses = PassBooksDAO.getInstance().create(new JSONObject(savedString));
                } else {
                    savedPasses = new PassBooksDTO();
                    savedPasses.setPasses(new ArrayList<PassBookDTO>());
                }
            }
        } catch (JSONException je) {
            LogHelper.logE(TAG, "Error getting saved Passes from preferences!!!!");
        }

        return savedPasses;
    }

    public PassBooksDTO getSavedPassesByCompany(Context context, PassBookCompanyDTO company) {
        PassBooksDTO companyPasses = new PassBooksDTO();
        companyPasses.setPasses(new ArrayList<PassBookDTO>());
        try {
            if (context != null) {
                String savedString = preferences(context).getString(PASSES, "{}");
                PassBooksDTO savedPasses = null;
                if (savedString != null) {
                    savedPasses = PassBooksDAO.getInstance().create(new JSONObject(savedString));
                } else {
                    savedPasses = new PassBooksDTO();
                    savedPasses.setPasses(new ArrayList<PassBookDTO>());
                }
                for (PassBookDTO pass : savedPasses.getPasses()) {
                    if (pass.getTeamIdentifier() != null
                            && pass.getTeamIdentifier().equals(company.getTeamIdentifier())) {
                        companyPasses.getPasses().add(pass);
                    }
                }
            }
        } catch (JSONException je) {
            LogHelper.logE(TAG, "Error getting saved Passes from preferences!!!!");
        }

        return companyPasses;
    }

    public boolean deletePass(Context context, PassBookDTO pass) {
        boolean deleted = false;
        if (context != null) {
            PassBooksDTO savedPasses = getSavedPasses(context);
            if (savedPasses.getPasses().contains(pass)) {
                savedPasses.getPasses().remove(pass);
            }
            savePasses(context, savedPasses);
            deleted = true;
        }
        return deleted;
    }

    public PassBookCompaniesDTO getCurrentCompanies(ArrayList<PassBookDTO> currentPasses) {
        PassBookCompaniesDTO companies = new PassBookCompaniesDTO();
        ArrayList<PassBookCompanyDTO> currentCompanies = new ArrayList<>();
        for (PassBookDTO pass : currentPasses) {
            PassBookCompanyDTO currentCompany = new PassBookCompanyDTO();
            currentCompany.setOrganizationName(pass.getOrganizationName());
            currentCompany.setTeamIdentifier(pass.getTeamIdentifier());
            // If this company is not created yet
            if (!currentCompany.existsInside(currentCompanies)) {
                currentCompany.setPassbooksCounter(1);
                currentCompanies.add(currentCompany);
            } else {
                for (PassBookCompanyDTO companyDTO : currentCompanies) {
                    if (companyDTO.getTeamIdentifier() != null &&
                            companyDTO.getTeamIdentifier().equals(currentCompany.getTeamIdentifier())) {
                        companyDTO.setPassbooksCounter(companyDTO.getPassbooksCounter() + 1);
                    }
                }
            }
        }
        companies.setCompanies(currentCompanies);
        return companies;
    }

    public void saveSelectedSort(Context context, Sort type) {
        if (context != null) {
            preferences(context).edit().putString(SORT_BY, type.name()).apply();
        }
    }

    public String getSortType(Context context) {
        String defaultSort = Sort.NONE.name();
        if (context != null) {
            defaultSort = preferences(context).getString(SORT_BY, "{}") != null ? preferences(context).getString(SORT_BY, "{}") : Sort.NONE.name();
        }

        return defaultSort;
    }

    public String getDateFormat(Context context) {
        String defaultFormat = PassBookDTO.RELEVANT_DATE_HUMAN_FORMAT;
        if (context != null) {
            defaultFormat = preferences(context).getString(DATE_FORMAT, "{}") != null ? preferences(context).getString(DATE_FORMAT, "{}") : defaultFormat;
        }
        return defaultFormat;
    }

    public void saveDateFormat(Context context, String dateFormat) {
        if (context != null) {
            preferences(context).edit().putString(DATE_FORMAT, dateFormat).apply();
        }
    }

    public boolean getPastDateActive(Context context) {
        boolean active = false;
        if (context != null) {
            String value = preferences(context).getString(HIDE_PASSED_EVENTS, "{}");
            active = Boolean.valueOf(value != null ? value : "false");
        }
        return active;
    }

    public void savePastDateActive(Context context, boolean active) {
        if (context != null) {
            preferences(context).edit().putString(HIDE_PASSED_EVENTS, String.valueOf(active)).apply();
        }
    }

    public ArrayList<PassBookDTO> getPassesWithoutArchivedOnesIfNeeded(Context context, ArrayList<PassBookDTO> passes) {
        ArrayList<PassBookDTO> currentPasses = new ArrayList<PassBookDTO>();

        if (context != null) {
            // Archive functionality is active, filter then
            if (getPastDateActive(context)) {
                long currentDatetime = (new Date()).getTime();
                for (PassBookDTO pass : passes) {
                    long passRelevantDatetime = pass.getRelevantDatetime();
                    // If the pass date has not passed
                    if (currentDatetime - passRelevantDatetime < Consts.A_DAY_IN_MILLIS) {
                        currentPasses.add(pass);
                    }
                }
            } else {
                currentPasses = passes;
            }
        }
        return currentPasses;
    }

    public ArrayList<PassBookDTO> getArchivedPasses(Context context, ArrayList<PassBookDTO> passes) {

        ArrayList<PassBookDTO> archivedPasses = passes;
        if (context != null) {
            // Archive functionality is active
            if (getPastDateActive(context)) {
                archivedPasses = new ArrayList<PassBookDTO>();
                long currentDatetime = (new Date()).getTime();
                for (PassBookDTO pass : passes) {
                    long passRelevantDatetime = pass.getRelevantDatetime();
                    // If the pass date has not passed
                    if (currentDatetime - passRelevantDatetime > Consts.A_DAY_IN_MILLIS) {
                        archivedPasses.add(pass);
                    }
                }
            }
        }
        return archivedPasses;
    }

    public enum Sort {
        UPDATE_DATE, RELEVANT_DATE, NONE
    }

    private static class FavoritesLogicHolder {
        public static final PreferencesHelper instance = new PreferencesHelper();
    }


}
