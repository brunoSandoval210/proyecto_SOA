import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShareWithUsersComponent } from './share-with-users.component';

describe('ShareWithUsersComponent', () => {
  let component: ShareWithUsersComponent;
  let fixture: ComponentFixture<ShareWithUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShareWithUsersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShareWithUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
