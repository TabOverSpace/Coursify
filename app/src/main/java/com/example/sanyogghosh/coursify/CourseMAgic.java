package com.example.sanyogghosh.coursify;


public class CourseMAgic {
    int mNumOfTabs;

    public CourseMAgic(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                Recommended tab0 = new Recommended();
                return tab0;
            case 1:
                First tab1 = new First();
                return tab1;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}