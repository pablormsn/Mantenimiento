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
  stages: [
    { duration: '3m', target: 4043 }, // ramp up to 80,000 VUs in 3 minutes
    { duration: '3m', target: 4043 }, // stay at 80,000 VUs for 3 minutes
    { duration: '2m', target: 0 },     // ramp down to 0 VUs in 2 minutes
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'], // <1% errors
    http_req_duration: ['p(95)<1000'], // 95% of requests must complete below 1000ms
  },
};

export default function () {
  let res = http.get('http://localhost:8080/medico/1');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1); // Adding sleep to simulate real-world user wait time between requests
}
