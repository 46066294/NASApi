package mysupercompany.nasapi;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Clase para crear Content Provider
 *
 * Created by Mat on 07/01/2017.
 */

public class NasApiContentProvider extends CupboardContentProvider {

    public NasApiContentProvider() {
        super(AUTHORITY, 1);
    }

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Photo.class);
    }


}
