// src/app/components/pie-chart/pie-chart.component.ts

import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { ChartService } from '../service/chart.service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss']
})
export class PieChartComponent implements OnInit {
  private chart!: Chart<'pie', number[], string>;

  constructor(private chartService: ChartService) {
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.chartService.getChartData().subscribe(data => {
      this.createChart(data);
    });
  }

  private createChart(data: { Equities: string; Bonds: string }): void {
    const ctx = document.getElementById('pieChart') as HTMLCanvasElement;

    // Replace comma with dot for correct parsing
    const equities = parseFloat(data.Equities.replace(',', '.'));
    const bonds = parseFloat(data.Bonds.replace(',', '.'));

    this.chart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: ['Equities', 'Bonds'],
        datasets: [{
          data: [equities, bonds],
          backgroundColor: ['#36A2EB', '#FF6384'],
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom',
          },
          title: {
            display: true,
            text: 'Pie Chart of Asset Values'
          }
        }
      }
    });
  }
}
