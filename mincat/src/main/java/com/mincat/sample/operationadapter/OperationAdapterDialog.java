package com.mincat.sample.operationadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;


import com.mincat.sample.R;

import java.util.List;

/**
 * @author Ming
 * @描述 自定义弹出框
 * @简介 将弹出框单利出来
 */

public class OperationAdapterDialog {


    // 禁止创建此类对象
    private OperationAdapterDialog() {

    }

    private static OperationAdapterDialog showDialog = new OperationAdapterDialog();

    public static OperationAdapterDialog getInstance() {

        return showDialog;

    }

    private TextView title;
    private Button cancel;
    private Button confirm;
    private AlertDialog dialog;
    private View view;
    private AlphaAnimation alphaAnimation;
    private AlertDialog.Builder builder;
    // 动画持续时间
    private int animation_duration = 200;
    // 动画开始透明度
    private float animation_from = 05.f;
    // 动画结束透明度
    private float animation_to = 1.0f;

    /**
     * 删除 RecycleViewItem条目
     *
     * @param activity   当前类
     * @param parseColor 标题颜色
     * @param titleName  标题名
     * @param position   adapter中的位置
     * @param lists      加载数据的List集合
     * @param adapter    加载的adapter
     */
    public void recycleViewRemoveItem(final Activity activity, String parseColor, String titleName, final int position, final List<?> lists, final RecyclerView.Adapter adapter, final RemoveItemCallBack callBack) {


        initialization(activity, parseColor, titleName);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callBack.removeItemSuccess(position, lists, adapter, dialog);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.removeItemCancel(dialog);
            }
        });

    }


    /**
     * @param activity   当前类
     * @param parseColor 标题颜色
     * @param titleName  标题名
     * @param list       加载数据的List集合
     * @param adapter    加载的adapter
     * @描述 清空Adapter
     */
    public void emptyAdapter(final Activity activity, String parseColor, String titleName, final List<?> list, final RecyclerView.Adapter adapter, final RemoveAllItemCallBack callBack) {


        initialization(activity, parseColor, titleName);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list != null && list.size() > 0) {

                    callBack.removeAllItemSuccess(list, adapter, dialog);
                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callBack.removeAllItemCancel(dialog);
            }
        });

    }



    /**
     * 初始化 dialog相关参数
     *
     * @param activity   当前类
     * @param parseColor 提示文字颜色
     * @param titleName  标题名
     */
    private void initialization(final Activity activity, String parseColor, String titleName) {
        alphaAnimation = new AlphaAnimation(animation_from, animation_to);
        alphaAnimation.setDuration(animation_duration);
        builder = new AlertDialog.Builder(activity);


        view = View.inflate(activity, R.layout.custom_dialog_adapter, null);
        title = (TextView) view.findViewById(R.id.title);

        title.setText(titleName);
        title.setTextColor(Color.parseColor(parseColor));

        confirm = (Button) view.findViewById(R.id.confirm);
        confirm.setTextColor(Color.parseColor(parseColor));

        cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setTextColor(Color.parseColor(parseColor));

        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

    }

    /**
     * @param position 需要删除的位置
     * @param lists    加载数据的List集合
     * @param adapter  加载的adapter
     * @描述 删除adapter中的某一个条目
     */
    public void removeItemInAdapter(final int position, final List<?> lists, final RecyclerView.Adapter adapter) {

        lists.remove(position);

        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();


    }

    /**
     * @param lists   传入加载adapter 的集合
     * @param adapter 加载数据的List集合
     * @描述 删除adapter中的全部条目
     */
    public void removeAllItemInAdapter(final List<?> lists, final RecyclerView.Adapter adapter) {

        lists.clear();

        adapter.notifyDataSetChanged();


    }


}
