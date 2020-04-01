package com.example.a3130_group17_project;

import android.view.View;

/** Interface for adding an onclick listener to recyclerview items.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public interface RecyclerViewClickListener {
    void onItemClick(Product p,View view);
}
