package per.goweii.basic.ui.dialog;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;
import per.goweii.basic.ui.R;
import per.goweii.basic.utils.listener.SimpleCallback;

/**
 * @author Cuizhen
 * @date 2018/6/21-上午10:00
 */
public class TipDialog {

    private final Context context;
    private CharSequence title;
    private CharSequence msg;
    private CharSequence yesText;
    private CharSequence noText;
    private boolean singleBtnYes = false;
    private boolean cancelable = true;
    private SimpleCallback<Void> callbackYes = null;
    private SimpleCallback<Void> callbackNo = null;
    private SimpleCallback<Void> onDismissListener = null;

    public static TipDialog with(Context context) {
        return new TipDialog(context);
    }

    private TipDialog(Context context) {
        this.context = context;
    }

    public TipDialog yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public TipDialog yesText(@StringRes int yesText) {
        this.yesText = context.getString(yesText);
        return this;
    }

    public TipDialog noText(CharSequence noText) {
        this.noText = noText;
        return this;
    }

    public TipDialog noText(@StringRes int noText) {
        this.noText = context.getString(noText);
        return this;
    }

    public TipDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public TipDialog title(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public TipDialog message(CharSequence msg) {
        this.msg = msg;
        return this;
    }

    public TipDialog message(@StringRes int msg) {
        this.msg = context.getString(msg);
        return this;
    }

    public TipDialog singleYesBtn() {
        singleBtnYes = true;
        return this;
    }

    public TipDialog cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public TipDialog onYes(SimpleCallback<Void> callback) {
        callbackYes = callback;
        return this;
    }

    public TipDialog onNo(SimpleCallback<Void> callback) {
        callbackNo = callback;
        return this;
    }

    public TipDialog onDismissListener(SimpleCallback<Void> onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public void show() {
        AnyLayer.with(context)
                .contentView(R.layout.basic_ui_dialog_tip)
                .backgroundColorRes(R.color.dialog_bg)
                .cancelableOnTouchOutside(cancelable)
                .cancelableOnClickKeyBack(cancelable)
                .onVisibleChangeListener(new LayerManager.OnVisibleChangeListener() {
                    @Override
                    public void onShow(AnyLayer anyLayer) {
                    }

                    @Override
                    public void onDismiss(AnyLayer anyLayer) {
                        if (onDismissListener != null) {
                            onDismissListener.onResult(null);
                        }
                    }
                })
                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyLayer) {
                        TextView tvYes = anyLayer.getView(R.id.basic_ui_tv_dialog_tip_yes);
                        TextView tvNo = anyLayer.getView(R.id.basic_ui_tv_dialog_tip_no);
                        View vLine = anyLayer.getView(R.id.basic_ui_v_dialog_tip_line);

                        if (singleBtnYes) {
                            tvNo.setVisibility(View.GONE);
                            vLine.setVisibility(View.GONE);
                        } else {
                            if (noText != null) {
                                tvNo.setText(noText);
                            } else {
                                tvNo.setText(R.string.basic_ui_dialog_btn_no);
                            }
                        }

                        if (yesText != null) {
                            tvYes.setText(yesText);
                        } else {
                            tvYes.setText(R.string.basic_ui_dialog_btn_yes);
                        }

                        TextView tvTitle = anyLayer.getView(R.id.basic_ui_tv_dialog_tip_title);
                        if (title == null) {
                            tvTitle.setVisibility(View.GONE);
                        } else {
                            tvTitle.setText(title);
                        }

                        TextView tvContent = anyLayer.getView(R.id.basic_ui_tv_dialog_tip_content);
                        tvContent.setText(msg);
                    }
                })
                .gravity(Gravity.CENTER)
                .onClickToDismiss(new LayerManager.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        if (callbackYes != null) {
                            callbackYes.onResult(null);
                        }
                    }
                }, R.id.basic_ui_tv_dialog_tip_yes)
                .onClickToDismiss(new LayerManager.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        if (callbackNo != null) {
                            callbackNo.onResult(null);
                        }
                    }
                }, R.id.basic_ui_tv_dialog_tip_no)
                .show();
    }
}
