 RequestQueue queue = Volley.newRequestQueue(this);
 StringRequest strRequest = new StringRequest(Request.Method.POST, "apiURL",
         new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 try {
                     JSONObject o = new JSONObject(response);
                     JSONArray values = o.getJSONArray("sonuc");
                     for (int i = 0; i < values.length(); i++) {
                         JSONObject sonuc = values.getJSONObject(i);
                     }
                 } catch (JSONException ex) {
                 }
             }
         },
         new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(volleyRequest.this, error.toString(), Toast.LENGTH_SHORT).show();
             }
         }) {
     @Override
     protected Map<String, String> getParams() {
         Map<String, String> params = new HashMap<String, String>();
         params.put("put1", "value1");
         params.put("put2", "value3");
         return params;
     }
 };
 strRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
 queue.add(strRequest);