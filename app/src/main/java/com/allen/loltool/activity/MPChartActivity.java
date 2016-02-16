package com.allen.loltool.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.allen.loltool.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Allen on 2016/1/4.
 */
public class MPChartActivity extends AppCompatActivity {
    LineChartView lineChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpchart);
        lineChartView = (LineChartView) findViewById(R.id.linechart);
        setLineChart();
    }

    private void setLineChart() {
        //填充数据
        List<PointValue> values = new ArrayList<>();
        //List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            values.add(new PointValue(i, (float) Math.random() * 100));
            //axisValues.add(new AxisValue(i).setLabel(chartDatas.get(i).getHour()));
        }
        List<Line> lines = new ArrayList<>();
        Line line = new Line(values);
        line.setColor(getResources().getColor(R.color.yellow));
        line.setCubic(true);
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //X轴
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(getResources().getColor(R.color.yellow));
        axisX.setMaxLabelChars(3);
        axisX.setName("使用时间");
        //axisX.setValues(axisValues);
        data.setAxisXBottom(axisX);
        //Y轴
        Axis axisY = new Axis();
        axisY.setHasTiltedLabels(true);
        axisY.setTextColor(getResources().getColor(R.color.yellow));
        axisY.setMaxLabelChars(3);
        axisY.setName("使用频率");
        data.setAxisYLeft(axisY);
//        Axis axisY = new Axis();  //Y轴
//        axisY.setMaxLabelChars(3); //默认是3，只能看最后三个数字
//        data.setAxisYLeft(axisY);

        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        //lineChartView.setZoomType(ZoomType.HORIZONTAL);
        //lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.setLineChartData(data);

    }
}
