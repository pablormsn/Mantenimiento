/*
 * Grupo:
 * Jorge Velázquez Jiménez
 * Pablo Ruiz Galiánez
 * Pablo Robles Mansilla
 * 
 */
import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  scenarios: {
    ramping_test: {
      executor: 'ramping-arrival-rate',
      startRate: 0,
      timeUnit: '1s', // unit is needed for the `startRate` and `predefined arrival rate`
      stages: [
        { target: 100000, duration: '10m' }, // ramp up to 100,000 VUs over 10 minutes
      ],
      preAllocatedVUs: 100, // start with 100 VUs
      maxVUs: 100000, // up to 100,000 VUs
    },
  },
  thresholds: {
    'http_req_failed': ['rate<0.01'], // <1% errors
    'http_req_duration': ['p(95)<1000'], // 95% of requests must complete below 1000ms
  },
};

export default function () {
  let res = http.get('http://localhost:8080/medico/1');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1); // Simulate real-world user wait time between requests
}
