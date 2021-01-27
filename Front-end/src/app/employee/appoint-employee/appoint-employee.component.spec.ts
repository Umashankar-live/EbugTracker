import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointEmployeeComponent } from './appoint-employee.component';

describe('AppointEmployeeComponent', () => {
  let component: AppointEmployeeComponent;
  let fixture: ComponentFixture<AppointEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppointEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
