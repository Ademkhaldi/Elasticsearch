import { TestBed } from '@angular/core/testing';

import { DataAumService } from './data-aum.service';

describe('DataAumService', () => {
  let service: DataAumService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataAumService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
