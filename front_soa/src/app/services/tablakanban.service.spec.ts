import { TestBed } from '@angular/core/testing';

import { TablakanbanService } from './tablakanban.service';

describe('TablakanbanService', () => {
  let service: TablakanbanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TablakanbanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
