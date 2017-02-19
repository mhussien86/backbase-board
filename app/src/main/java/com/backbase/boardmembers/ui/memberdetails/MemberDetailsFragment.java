package com.backbase.boardmembers.ui.memberdetails;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.backbase.boardmembers.R;
import com.backbase.boardmembers.data.APIConstants;
import com.backbase.boardmembers.models.MembersResponseDTO;
import com.backbase.boardmembers.ui.AppConstants;
import com.backbase.boardmembers.utils.MailUtil;
import com.backbase.boardmembers.utils.MemberPhotoUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.parceler.Parcels;

/**
 * Created by mohamed on 18/02/17.
 */
public class MemberDetailsFragment extends BottomSheetDialogFragment {

    View contentView;

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        contentView = View.inflate(getContext(), R.layout.member_details_layout, null);
        dialog.setContentView(contentView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle mArgs = getArguments();

        final MembersResponseDTO.MemberDetails memberDetails = Parcels.unwrap(mArgs.getParcelable(AppConstants.MEMBER_DETAILS));

        final ImageView memberPhoto = (ImageView) contentView.findViewById(R.id.itemImg);
        Glide.with(getActivity()).load(APIConstants.IMAGE_SERVICE_PATH + memberDetails.getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.avatar).centerCrop().into(memberPhoto);
        memberPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MemberPhotoUtil().zoomImageFromThumb(memberPhoto, contentView);
            }
        });

        TextView memberName = (TextView) contentView.findViewById(R.id.member_name_details);
        memberName.setText(getString(R.string.member_full_name, memberDetails.getName(), memberDetails.getSurname()));

        Button sendMailButton = (Button) contentView.findViewById(R.id.button_send);
        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MailUtil.openMailClient(getContext(),memberDetails.getEmail(),"");
            }
        });

        TextView memberDepartment = (TextView) contentView.findViewById(R.id.department_textview);
        memberDepartment.setText(memberDetails.getDepartment());

        TextView memberRole = (TextView) contentView.findViewById(R.id.role_textview);
        memberRole.setText(memberDetails.getRole());
    }

}