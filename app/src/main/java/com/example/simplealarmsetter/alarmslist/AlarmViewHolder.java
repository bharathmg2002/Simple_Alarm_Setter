package com.example.simplealarmsetter.alarmslist;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplealarmsetter.data.Alarm;
import com.example.simplealarmsetter.R;

public class AlarmViewHolder extends RecyclerView.ViewHolder {
    private TextView alarmTime;
    private ImageView alarmRecurring;
    private TextView alarmRecurringDays;
    private TextView alarmTitle;

    Switch alarmStarted;

    private OnToggleAlarmListener listener;

    public AlarmViewHolder(@NonNull View itemView, OnToggleAlarmListener listener) {
        super(itemView);

        alarmTime = itemView.findViewById(R.id.item_alarm_time);
        alarmStarted = itemView.findViewById(R.id.item_alarm_started);
        alarmRecurring = itemView.findViewById(R.id.item_alarm_recurring);
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays);
        alarmTitle = itemView.findViewById(R.id.item_alarm_title);

        this.listener = listener;
    }

    public void bind(Alarm alarm) {
        String alarmText = String.format("%02d:%02d", alarm.getHour(), alarm.getMinute());
        // int hour24 = alarm.getHour();
        //  int minute = alarm.getMinute();

        //  String alarmText = String.format(convertTo12HourFormat(hour24, minute));
        alarmTime.setText(alarmText);
        alarmStarted.setChecked(alarm.isStarted());

        if (alarm.isRecurring()) {
            alarmRecurring.setImageResource(R.drawable.ic_repeat_black_24dp);
            alarmRecurringDays.setText(alarm.getRecurringDaysText());
        } else {
            alarmRecurring.setImageResource(R.drawable.ic_looks_one_black_24dp);
            alarmRecurringDays.setText("Once Off");
        }

        if (alarm.getTitle().length() != 0) {
            alarmTitle.setText(String.format("%s ", alarm.getTitle()));
        } else {
            alarmTitle.setText(String.format("%s ", "Alarm"));
        }

        alarmStarted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onToggle(alarm);
            }
        });
    }
   /* private String convertTo12HourFormat(int hour24, int minute) {
        String amPm;
        int hour12 = hour24;

        if (hour12 >= 12) {
            amPm = "PM";
            if (hour12 > 12) {
                hour12 -= 12;
            }
        } else {
            amPm = "AM";
            if (hour12 == 0) {
                hour12 = 12;
            }
        }

        return String.format("%02d:%02d %s", hour12, minute, amPm);
    }

    */
}