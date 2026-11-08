import React from 'react';

interface LogoProps {
  className?: string;
  size?: 'sm' | 'md' | 'lg';
  showSubtitle?: boolean;
}

export const ElevoraLogo: React.FC<LogoProps> = ({ 
  className = '', 
  size = 'md',
  showSubtitle = false 
}) => {
  const iconSizes = {
    sm: 'w-6 h-5',
    md: 'w-8 h-7',
    lg: 'w-10 h-8'
  };

  const textSizes = {
    sm: 'text-lg',
    md: 'text-2xl',
    lg: 'text-3xl'
  };

  return (
    <div className={`flex items-center gap-2.5 select-none ${className}`}>
      {/* 3 slanted gold bars matching exact Elevora brand mark */}
      <svg 
        className={`${iconSizes[size]} shrink-0`} 
        viewBox="0 0 36 32" 
        fill="none" 
        xmlns="http://www.w3.org/2000/svg"
      >
        <polygon points="6,2 34,2 29,9 1,9" fill="#F5A623" />
        <polygon points="5,12 25,12 21,19 1,19" fill="#F5A623" />
        <polygon points="4,22 32,22 27,29 0,29" fill="#F5A623" />
      </svg>

      {/* Brand Name Typography */}
      <div className="flex flex-col leading-none">
        <div className="flex items-center">
          <span className={`font-extrabold text-white tracking-tight ${textSizes[size]} font-display`}>
            Elevora
          </span>
          <span className="text-[#F5A623] text-xs font-bold ml-0.5 -mt-2">™</span>
        </div>
        {showSubtitle && (
          <span className="text-[10px] text-slate-400 font-medium tracking-wide mt-1">
            Professional English Learning Platform
          </span>
        )}
      </div>
    </div>
  );
};
