import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { ChartService } from '../service/chart.service';

@Component({
  selector: 'app-histogram',
  templateUrl: './histogram.component.html',
  styleUrls: ['./histogram.component.scss']
})
export class HistogramComponent implements OnInit {

  public histogramChart: any;

  constructor(private chartService: ChartService) { }

  ngOnInit(): void {
    this.loadHistogramData();
  }

  loadHistogramData(): void {
    this.chartService.getHistogramData().subscribe(data => {
      const labels = Object.keys(data);
      const values = Object.values(data);

      this.histogramChart = new Chart('histogramChart', {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: 'Valeur par Date',
            data: values,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            x: {
              beginAtZero: true
            }
          }
        }
      });
    });
  }
}
