/*
 * Copyright (C) 2015-2016 Willi Ye <williye97@gmail.com>
 *
 * This file is part of Kernel Adiutor.
 *
 * Kernel Adiutor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kernel Adiutor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kernel Adiutor.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.hitman.kernelmanager.fragments.statistics;

import android.content.res.Configuration;

import com.hitman.kernelmanager.R;
import com.hitman.kernelmanager.fragments.RecyclerViewFragment;
import com.hitman.kernelmanager.utils.Device;
import com.hitman.kernelmanager.utils.Utils;
import com.hitman.kernelmanager.views.recyclerview.DescriptionView;
import com.hitman.kernelmanager.views.recyclerview.RecyclerViewItem;

import java.util.List;

/**
 * Created by willi on 05.08.16.
 */
public class MemoryFragment extends RecyclerViewFragment {

    @Override
    protected boolean showViewPager() {
        return false;
    }

    @Override
    public int getSpanCount() {
        int span = Utils.isTablet(getActivity()) ? Utils.getOrientation(getActivity()) ==
                Configuration.ORIENTATION_LANDSCAPE ? 5 : 4 : Utils.getOrientation(getActivity()) ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        if (itemsSize() != 0 && span > itemsSize()) {
            span = itemsSize();
        }
        return span;
    }

    @Override
    protected void addItems(List<RecyclerViewItem> items) {
        List<String> mems = Device.MemInfo.getItems();
        for (String mem : mems) {
            DescriptionView memView = new DescriptionView();
            memView.setTitle(mem);
            memView.setSummary(Device.MemInfo.getItem(mem).replace(" ", "")
                    .replace("kB", getString(R.string.kb)));

            items.add(memView);
        }
    }

}
