package es.claucookie.pasbuk.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import es.claucookie.pasbuk.fragments.BackInfoFragment_;
import es.claucookie.pasbuk.fragments.FrontInfoFragment_;
import es.claucookie.pasbuk.fragments.PassSettingsFragment_;
import es.claucookie.pasbuk.model.dto.PassBookDTO;

/**
 * Created by claucookie on 29/03/15.
 */
public class PassbookContentPagerAdapter extends FragmentPagerAdapter {

    private PassBookDTO passBookDTO;
    private Fragment[] fragments;
    private FragmentManager manager;

    public PassbookContentPagerAdapter(FragmentManager fm, PassBookDTO passBookDTO) {
        super(fm);
        this.passBookDTO = passBookDTO;
        fragments = new Fragment[getCount()];
        manager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment = fragments[position];
        if (returnFragment == null) {
            switch (position) {
                case 0:
                    returnFragment = FrontInfoFragment_.builder().passBookDTO(passBookDTO).build();
                    break;
                case 1:
                    returnFragment = BackInfoFragment_.builder().passBookDTO(passBookDTO).build();
                    break;
                case 2:
                    returnFragment = PassSettingsFragment_.builder().passBookDTO(passBookDTO).build();
                    break;
            }
            fragments[position] = returnFragment;
        }
        return returnFragment;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment res = fragments[position];
        if (res != null && manager != null && !manager.isDestroyed()) {
            manager.beginTransaction().remove(res).commit();
        }
        fragments[position] = null;
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 3;
    }

}