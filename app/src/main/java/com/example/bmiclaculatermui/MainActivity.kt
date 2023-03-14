package com.example.bmiclaculatermui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bmiclaculatermui.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var isclear: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.calculate.setOnClickListener(this)

        if (isclear) {
            isclear = false
            binding.calculate.setText("calculate")
            binding.weight.isEnabled=true
            binding.hight.isEnabled=true
        }
    }


    override fun onClick(View: View?) {
        when (View?.id) {
            R.id.calculate -> {
                if (binding.hight.editableText.isEmpty() && binding.weight.editableText.isEmpty()) {
                    binding.hight.requestFocus()
                    Toast.makeText(this, "please Enter weight and height", Toast.LENGTH_SHORT)
                        .show()
                } else if (binding.hight.editableText.isEmpty()) {
                    Toast.makeText(this, "please enter the hieght", Toast.LENGTH_SHORT).show()
                } else if (binding.weight.editableText.isEmpty()) {
                    binding.weight.requestFocus()
                    Toast.makeText(this, "please Enter the weight", Toast.LENGTH_SHORT).show()
                }

                if (isclear) {
                    isclear = false
                    binding.calculate.text = "calculate"
                    binding.textview1.setText("")
                    binding.textview2.setText("")

                    binding.weight.isEnabled=true
                    binding.hight.isEnabled=true

                    binding.hight.editableText.clear()
                    binding.weight.editableText.clear()

                    Toast.makeText(this, "clear now", Toast.LENGTH_SHORT).show()
                } else if (binding.hight.editableText.toString()
                        .isNotEmpty() && binding.weight.editableText.toString().isNotEmpty()
                ) {
                    if (!isclear) {
                        // inislize the variable
                        isclear = true
                        binding.calculate.setText("clear")

                        binding.weight.isEnabled=false
                        binding.hight.isEnabled=false


                        val hi = (binding.hight.editableText.toString().toDouble())
                        val wi = (binding.weight.editableText.toString().toDouble())


                        if (hi == 0.0 || wi == 0.0) {
                            Toast.makeText(this, "Invalid value", Toast.LENGTH_SHORT).show()
                        } else {
                            val hieght = hi.toFloat() / 100
                            val BMI = wi.toFloat() / (hieght * hieght)

                            val total = (round(BMI * 100) / 100.0)

                            binding.textview1.text =
                                "your BMI value= ${total}"// this is the bmi value

                            if (total < 18) {
                                binding.textview2.text = "you are under weight"
                            } else if (total >= 18 && total < 25) {
                                binding.textview2.text = "you are healthy"
                            } else if (total > 25) {
                                binding.textview2.text = "you are over weight"
                            }


                        }


                    }
                }

            }

        }

    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.item, menu)

            return super.onCreateOptionsMenu(menu)
        }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.item1 -> {
                    val intent = Intent(this, thirdActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "about developer", Toast.LENGTH_SHORT).show()
                    return true
                }
                R.id.item2 -> {
                    val intent = Intent(this, secoundActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "BMI Chart", Toast.LENGTH_SHORT).show()
                    return true
                }
                R.id.item3 -> {
                    finish()
                    System.exit(0)
                    Toast.makeText(this, "closed app", Toast.LENGTH_SHORT).show()
                    return true
                }

                R.id.item4 -> {
                   // val intent = Intent(this, secoundActivity::class.java)
                   // startActivity(intent)
                    Toast.makeText(this, "contect to user", Toast.LENGTH_SHORT).show()
                    return true
                }


                R.id.item5 -> {
                    // val intent = Intent(this, secoundActivity::class.java)
                    // startActivity(intent)
                    Toast.makeText(this, "what is BMI", Toast.LENGTH_SHORT).show()

                    val intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/healthyweight/assessing/bmi/index.html"))
                    startActivity(intent)

                }




                R.id.subitem1 -> {
                    // val intent = Intent(this, secoundActivity::class.java)
                    // startActivity(intent)
                    Toast.makeText(this, "Dail phone no. of developer", Toast.LENGTH_SHORT).show()

                    val intent=Intent(Intent.ACTION_DIAL, Uri.parse("tel:7570077927"))
                    startActivity(intent)
                    return true
                }




                R.id.subitem2 -> {
                    // val intent = Intent(this, secoundActivity::class.java)
                    // startActivity(intent)
                    Toast.makeText(this, "calling to user", Toast.LENGTH_SHORT).show()


                    if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){

                        val intent=Intent(Intent.ACTION_CALL)
                        intent.data=Uri.parse("tel:6387511508")
                        startActivity(intent)


                    }
                    else{
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 1001)
                    }
                }





                R.id.subitem3 -> {
                    Toast.makeText(this, "send to email", Toast.LENGTH_SHORT).show()

                    val intent=Intent(Intent.ACTION_SENDTO).apply {
                        data= Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("abhishekshuklagola82@gmail.com"))
                      //  putExtra(Intent.EXTRA_SUBJECT,"my work is good")
                    }
                    startActivity(intent)

                }











            }


            return super.onOptionsItemSelected(item)
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1001){
            if(grantResults.isNotEmpty() && permissions[0].equals(PackageManager.PERMISSION_GRANTED)){

            }else{
                Toast.makeText(this, "Please give permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
      Toast.makeText(this,"please go to option menu and press the exit option",Toast.LENGTH_LONG).show()
    }

    }










